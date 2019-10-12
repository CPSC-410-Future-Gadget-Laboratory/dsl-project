GET "/conditionalTrue" {
    VAR a = 1;
    VAR b = 2;
    IF (a < b) {
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
    VAR a = 1;
    VAR b = 2;
    IF (a > b) {
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