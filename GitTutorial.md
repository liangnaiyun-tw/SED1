# Git/Github Tutorial

This tutorial consists of the following four sections.
1. [Basic Concept](#basic-concept)
2. [How to submit your code to this repository?](#how-to-submit-your-code-to-this-repository)
3. [How to review others' PR?](#how-to-review-others-pr)
4. [Reference](#reference)

## Basic Concept

### Terminology

**Commit**

- a single point in the git history
- a commit can contain changes across multiple files

**Branch**

- a line of development
- a repository can track multiple branches, but your working tree is associated with just one of them
- the default branch is `main`
- can be categorized into local branches (exist only on your machine) and remote branches (located on a remote location, eg. Github)

**Pull Request (PR)**

- a process for a developer to notify others that they have completed their feature
- process
    1. The author creates a PR from his/her feature branch.
    2. The team members review the PR and leave comments. If a team member consider the changes as merge-ready, he/she can “approve” the PR; otherwise, he/she should leave his/her suggestions. The author should add new commits to address the issues.
    3. When the number of approvals meets the threshold, the author can merge the changes by completing the PR.

### Stages

The basic workflow of Git contains 4 stages.

- **working directory**
    - After editing the source code in your editor and save them to the file system, the changes are added to the working directory.
- **staging area**
    - By adding your changes with the `git add <filename>` command, you’ve tell Git that you want to include updates in the added files in the next commit.
    - You can check the staged/unstaged files via the `git status` command.
- **local repo**
    - By committing the staged changes with the `git commit -m "<commit message>"`, the staged changes are added to the Git directory.
    - You can check the commits on your current branch with the `git log --oneline` command.
- **remote repo**
    - To collaborate with others, you can push your changes to a remote repository with the `git push` command or pull others’ changes with the `git pull` command.

![https://i.imgur.com/aDU5pZT.png](https://i.imgur.com/aDU5pZT.png)

## How to submit your code to this repository?

### 1. Update main branch to the latest

- Purpose
    - ensure that your main branch is up-to-date
- How?
    1. `git checkout main`: switch to the main branch
    2. `git pull origin main`: pull the changes on the remote main branch to your local main branch
- Tips
    - To check which branch you’re currently on, use the `git branch` command. It lists all your local branches and mark your current branch with a `*`.

### 2. Create a new feature branch

- Purpose
    - implement the new feature on a new branch, so that the main branch is not affected until the new feature is well-implemented
- How?
    - `git checkout -b <new-branch>`: create a new branch off your current branch, name the new branch as `new-branch`, and switch to the branch
- Tips
    - You can check if you have successfully switched to the new branch with the `git branch` command.

### 3. Add, commit, and push the changes

- Purpose
    - add changes
- How?
    1. `git add <file-or-directory-to-add>`: stage the changes in a file or all files under a directory
    2. `git commit -m "<commit message>"`: create a new commit that consists of the staged changes
    3. `git push`: push the newly created commits of the local branch to the remote repository, note that if the branch has not been pushed to remote before, you should set the branch’s upstream during push by `git push --set-upstream origin <branch-name>`
- Tips
    - The `git add` command supports adding multiple files at a time, you can either list the files or add by directory
        - eg. `git add a_file b_file`: stages both `a_file` and `b_file`
        - eg. `git add a/`: stages all modified files under the directory `a/` (except those mentioned in `.gitignore`)

### 4. Create a PR

- Purpose
    - invite others to review your code
- How?
    1. Start a new PR
        
        ![https://i.imgur.com/G5KPMnh.png](https://i.imgur.com/G5KPMnh.png)
        
    2. Select your feature branch
        
        ![https://i.imgur.com/3x5rtZU.png](https://i.imgur.com/3x5rtZU.png)
        
    3. Check if the source branch, destination branch, and the changed files are correct
        
        ![https://i.imgur.com/P8TVCak.png](https://i.imgur.com/P8TVCak.png)
        
    4. Edit the PR description
        
        ![https://i.imgur.com/gMAGncC.png](https://i.imgur.com/gMAGncC.png)
        
- Tip
    - A good practice is to have your PR sticked to one single purpose. If your changes are related to multiple bugs/features, separate them into different PRs.

## How to review others' PR?

- How?
    1. Read the PR description and the comments from other reviewers
        
        ![https://i.imgur.com/C6EyuFY.png](https://i.imgur.com/C6EyuFY.png)
        
    2. Review the codes file by file, add comments to leave your question or suggestion
        
        ![https://i.imgur.com/0pbcE8V.png](https://i.imgur.com/0pbcE8V.png)
        
    3. Submit the review
        
        ![https://i.imgur.com/AZ0paC8.png](https://i.imgur.com/AZ0paC8.png)
        
- Tips
    - To keep your comment clear and concise, follow the format `<label> [decorations]: <subject>` as suggested in [conventionalcomments](https://conventionalcomments.org/). Here are some useful labels:
        - **praise**: highlight something positive
        - **nitpick**: trivial preference-based requests
        - **suggestion**: propose improvements to the current subject
        - **question**: your potential concern
        - **typo**: to point out a misspelled word

## Reference

- [Git - gitglossary Documentation (git-scm.com)](https://git-scm.com/docs/gitglossary)
- [Git Tutorial (w3schools.com)](https://www.w3schools.com/git/default.asp?remote=github)
- [Git, GitHub, & Workflow Fundamentals - DEV Community 👩‍💻👨‍💻](https://dev.to/mollynem/git-github--workflow-fundamentals-5496)
- [Conventional Comments](https://conventionalcomments.org/)