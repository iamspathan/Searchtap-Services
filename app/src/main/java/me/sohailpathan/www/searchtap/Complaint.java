package me.sohailpathan.www.searchtap;

public class Complaint {
    private String ComplainOrderNo;
    private String ComplaintMobileNumber;
    private String ComplaintEmail;
    private String ComplaintComment;


    public Complaint()
    {

    }

    public Complaint( String complainOrderNo, String complaintMobileNumber, String complaintEmail, String complaintComment) {
        ComplainOrderNo = complainOrderNo;
        ComplaintMobileNumber = complaintMobileNumber;
        ComplaintEmail = complaintEmail;
        ComplaintComment = complaintComment;
    }


    public String getComplainOrderNo() {
        return ComplainOrderNo;
    }

    public String getComplaintMobileNumber() {
        return ComplaintMobileNumber;
    }

    public String getComplaintEmail() {
        return ComplaintEmail;
    }

    public String getComplaintComment() {
        return ComplaintComment;
    }
}
