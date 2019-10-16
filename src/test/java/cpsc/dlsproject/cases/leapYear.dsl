GET "/isLeapYear{year}" {
  VAR isFactor4 : Number = == % year TO 4 TO 0;
  VAR isNotFactor100 : Number = == % year TO 100 TO 0;
  VAR isFactor400 : Number = % == year TO 400 TO 0;

  IF (|| && isFactor4 TO isNotFactor100 TO isFactor400) {
    SEND {
      200;
      "It is a leap year!";
    } ELSE {
        SEND {
          400;
          "It is not a leap year!";
        }
    }
  }
}