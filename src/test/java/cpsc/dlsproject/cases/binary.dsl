GET "/" {
  VAR a = 1;
  VAR b = 2;

  VAR c = true;
  VAR d = false;

  VAR addition = a + b;
  VAR subtraction = a - b;
  VAR multiplication = a * b;
  VAR division = a \ b;
  VAR andOp = c & d;
  VAR orOp = d | d;
  VAR lessThanOp = a < b;
  VAR lessThanOrEqualOp = a <= b;
  VAR equalOp = a == b;
  VAR notEqualOp = a != b;
  VAR greaterThanOp = a > b;
  VAR greaterOp = a >= b;

  SEND {
  "addition: {addition}, subtraction: {subtraction}, multiplication: {multiplication}, division: {division}, andOp: {andOp}, orOp: {orOp}, lessThanOp: {lessThanOp}, lessThanOrEqualOp: {lessThanOrEqualOp, equalOp: {equalOp}, notEqualOp: {notEqualOp}, greaterThanOp: {greaterThanOp}, greaterOp: {greaterOp}";
  200;
  }
};
