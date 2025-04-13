package com.e_commerce.e_commerce;

public class Response {

  public String message;
  public int status;

  public Response() {
    this.message = "";
    this.status = 0;
 }

  public Response(String message, int status) {
    this.message = message;
    this.status = status;
  }
  public void setMessage(String message){
    this.message = message;
  }
  public void setStatus(int status){
    this.status = status;
  }
  
}
