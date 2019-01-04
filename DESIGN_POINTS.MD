# Main Design Points
## Logging
slf4j + log4j2
## Testing
### Framework 
testNG
### Case Design
Addition to the invalid use case in the requirement doc, I think at least 
The following 2 cases need user friendly error msgs:
1. Divided by 0
2. Operator Casting Error. E.g if user input "abc", which can't be converted to either a number or an operator  
## Undo
Setup a separated stack to store all processed operations. When doing undo, call those operations' rollback methods.

## Command Line User Interface
Ideally I need to setup some configures in the cmd-line user interface. However I don't have enough time in this version.

1. display language (i18n)  ---  TODO, in next release:) 
2. Help info
3. Precision setup. The requirement asks 10 decimal places. However this could ideally be configurable.

The basic ideal is to use some java cli libary to build up those arguments(with help info) and parse them.
The candidate lib is the apache commons-cli.

## Exception Handling
As I mention in "case design" section. I handle two more exceptions in this version.
1. Divided by 0
2. Operator Casting Error.

### Split by more than one space
Moreover, I also handle the cases that users' input which carry more spaces.
In such cases, it will be difficult to trace the invalid input position.
This is already implemented. 

## Internationalization
I have this though for this "product", but no time to release in this version.

## Performance and Concurrent Computing

## Data Format
Override "toString" method in NumberElement could help us change the data format in follwoing version if the requirement is changed.

## Expandability  
By creating new OperatorCmd classes we can easiy expand the supported operators, e.g. redo, logN


