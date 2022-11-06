Write-Output "Remove compiled class..."
powershell /c "rm ..\code\*.class"
Write-Output "Compile java files..."
powershell /c "javac ..\code\*.java"

[string] $sampleInputs = Get-ChildItem -Name | Select-String -Pattern "sampleInput*"
foreach($sampleInput in $sampleInputs) {
    [string] $sampleInput = $sampleInput
    $extension = $sampleInput.Substring(11);
    $sampleOutput = ".\sampleOutput" + $extension;
    $expectedResult = gc ${sampleOutput} | %{$i = 1} {new-object psobject -prop @{Text=$_.Trim(); LineNum=$i}; $i++}

    Write-Output "`nSample input: $($sampleInput)"
    Write-Output "Sample output: $($sampleOutput.Substring(2))"

    $exeCommand = "cd ..\code; java Main ..\testcase\" + $sampleInput
    $resultFile = "..\testcase\result" + $extension
    powershell /c $exeCommand | Out-File -FilePath $resultFile
    $executeResult = gc ${resultFile} | %{$i = 1} {new-object psobject -prop @{Text=$_.Trim(); LineNum=$i}; $i++}
    
    Write-Output "`nCompare $($sampleOutput.Substring(2)) <--> $($resultFile.Substring(11))"
    $compareResult = Compare-Object $expectedResult $executeResult -Property Text -passThru -caseSensitive | Format-Table @{Name="Side"; Expression={$_.SideIndicator}}, LineNum, Text
    Write-Output $compareResult
    if ($compareResult) {
        throw "Test cases failed."
    }
}