GET "/isLeapYear" {
  VAR isFactor4 : Number = year % 4 == 0;
  VAR isNotFactor100 : Number = year % 100 != 0;
  VAR isFactor400 : Number = year % 400 == 0;

  IF ((isFactor4 && isNotFactor100) || isFactor400) {
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