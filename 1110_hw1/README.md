# Testcase document

## Case1 
- Description: Basic flow

- Input: 
```
TextView1 Hi
TextView2 Hello
TextView2 add scrollBar
TextView2 display
TextView3 HelloWorld
TextView3 add scrollBar thickBlackBorder
TextView3 display
TextView1 display
```

- Output: 
```
Hello scrollBar
HelloWorld scrollBar thickBlackBorder
Hi
```


## Case2
- Description: We add "stevet", not "scrollbar" or "thickBlackBorder", so input error.

- Input:
```
TextView1 Hi
TextView1 add steven
TextView1 display
```
- Output:
```
Input Error
Hi
```