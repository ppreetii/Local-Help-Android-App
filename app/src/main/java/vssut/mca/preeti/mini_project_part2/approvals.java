package vssut.mca.preeti.mini_project_part2;

public  class approvals {
    String name,branch,rollno,roomno,phoneNo,guardianNo,reason,leaveDate,leaveTime,returnDate,returnTime,travelMode,timestamp;
    RequestDetails requestDetails;
    approvals()
    {

    }
    approvals(String name, String branch, String rollno, String roomno, String phoneNo, String guardianNo, String reason, String leaveDate, String leaveTime, String returnDate, String returnTime, String travelMode,String timestamp) {
        this.name = name;
        this.branch = branch;
        this.rollno = rollno;
        this.roomno = roomno;
        this.phoneNo = phoneNo;
        this.guardianNo = guardianNo;
        this.reason = reason;
        this.leaveDate = leaveDate;
        this.leaveTime = leaveTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.travelMode = travelMode;
        this.timestamp=timestamp;

    }
    approvals(RequestDetails requestDetails,String timestamp)
    {
        this.requestDetails=requestDetails;
        this.timestamp=timestamp;
    }


}
