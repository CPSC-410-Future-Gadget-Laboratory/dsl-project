GET "/" {
  VAR a: Number = 1;
  VAR b: Number = 2;

  VAR c: Boolean = true;
  VAR d: Boolean = false;

  VAR addition: Number = + a TO b;
  VAR subtraction: Number = - a TO b;
  VAR multiplication: Number = * a TO b;
  VAR division: Number = \ a TO b;
  VAR andOp: Boolean = && c TO d;
  VAR orOp: Boolean = || c TO d;
  VAR lessThanOp: Boolean = < a TO b;
  VAR lessThanOrEqualOp: Boolean = <= a TO b;
  VAR equalOp: Boolean = == a TO b;
  VAR notEqualOp: Boolean = != a TO b;
  VAR greaterThanOp: Boolean = > a TO b;
  VAR greaterOp: Boolean = >= a TO b;

  SEND {
  "addition: {addition}, subtraction: {subtraction}, multiplication: {multiplication}, division: {division}, andOp: {andOp}, orOp: {orOp}, lessThanOp: {lessThanOp}, lessThanOrEqualOp: {lessThanOrEqualOp}, equalOp: {equalOp}, notEqualOp: {notEqualOp}, greaterThanOp: {greaterThanOp}, greaterOp: {greaterOp}";
  200;
  }
}
