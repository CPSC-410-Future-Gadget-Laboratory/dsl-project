GET "/conditionalTrue" {
    VAR a : Number = 1;
    VAR b : Number = 2;
    IF (< a TO b) {
      SEND {
        200;
        "something good";
      }
    } ELSE {
      SEND {
        500;
        "something bad";
      }
    }
}

GET "/conditionalFalse" {
    VAR a : Number = 1;
    VAR b : Number = 2;
    IF (> a TO b) {
      SEND {
        200;
        "something good";
      }
    } ELSE {
      SEND {
        500;
        "something bad";
      }
    }
}
