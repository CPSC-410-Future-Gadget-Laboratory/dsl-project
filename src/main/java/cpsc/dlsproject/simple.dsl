GET "/path/to/success" {
  SEND {
    200;
    "here is to your success!";
  }
}

GET "/" {
    SEND {
        200;
        "this is the home page";
    }
}

GET "/path" {
    SEND {
        500;
        "This is bad, sending a 500";
    }
}

GET "/to" {
    SEND {
        200;
        "Sending a 204";
    }
}

GET "/index" {
    SEND {
        404;
        "Sending a 404, page not found! alas";
    }
}

GET "/success" {
    SEND {
        400;
        "I wish everyone success!";
    }
}