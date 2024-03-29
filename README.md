This provides a framework for creating basic syntax trees. 

*__A note:__ This program was written before I was aware of the concept of context-free-grammar, lexical analysis, or Backus-Naur form. As a result, these programs bear a resemblence to some of these concepts, but they don't implement them correctly.*

### `Head()`

The `Head` object provides the basic methods to create and evaulate syntax trees.

Create a new branch with `Head(left, right, Operator)`. `left` and `right` can both either be branch objects or simple values (doubles or integers). `Operator` is one of the provided operators in the enum `Op`. 

*Example:*</br>
`Head h = new Head(3,4,Op.DIV)` would create a new branch which divides 3 by 4.</br> 
`Head g = new Head(new Head(5,2,Op.PLUS), 4, Op.MINUS)` would create a new branch which subtracts 4 from the sum of 5 and 2

`Head` provides the `calculate()` method to evaluate the tree as a whole and return a result as a double. 

*Example:*</br>
`h.calculate()` would return a value of 0.75</br>
`g.calculate()` would return a value of 3 [i.e. ((5+2) - 4)] </br>


### `TreeTraverse()`

The TreeTraverse class provides a way to move through the syntax tree and obtain a branch at any point in the tree. In this way, it can be thought of as a kind of cursor. 

*Methods:*
- `toLeft()`: moves the TreeTraverse to the left child of the current branch. 
- `toRight()`: moves the TreeTraverse to the right child of the current branch.
- `toParent()`: moves the TreeTraverse to the parent of the current branch. 

*Examples:*</br>
`h.toLeft()` returns the number 3</br>
`g.toLeft()` returns `Head(5,2,Op.PLUS)`</br>
`g.toRight()`, run again, returns the number 2

### `Tokenizer`

This project also contains a static method for turning an input string into tokens for future parsing.
