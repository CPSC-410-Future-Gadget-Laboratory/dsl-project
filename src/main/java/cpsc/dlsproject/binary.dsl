GET "/" {
  VAR a: Number = 1;
  VAR b: Number = 2;

  VAR c: Boolean = true;
  VAR d: Boolean = false;

  VAR addition: Number = a + b;
  VAR subtraction: Number = a - b;
  VAR multiplication: Number = a * b;
  VAR division: Number = a \ b;
  VAR andOp: Boolean = c & d;
  VAR orOp: Boolean = d | d;
  VAR lessThanOp: Boolean = a < b;
  VAR lessThanOrEqualOp: Boolean = a <= b;
  VAR equalOp: Boolean = a == b;
  VAR notEqualOp: Boolean = a != b;
  VAR greaterThanOp: Boolean = a > b;
  VAR greaterOp: Boolean = a >= b;

  SEND {
  "addition: {addition}, subtraction: {subtraction}, multiplication: {multiplication}, division: {division}, andOp: {andOp}, orOp: {orOp}, lessThanOp: {lessThanOp}, lessThanOrEqualOp: {lessThanOrEqualOp}, equalOp: {equalOp}, notEqualOp: {notEqualOp}, greaterThanOp: {greaterThanOp}, greaterOp: {greaterOp}";
  200;
  }
};
