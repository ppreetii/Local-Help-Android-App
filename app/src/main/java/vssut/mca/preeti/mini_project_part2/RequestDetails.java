package vssut.mca.preeti.mini_project_part2;

import java.io.Serializable;

public class RequestDetails implements Serializable {
    String name,branch,rollno,roomno,phoneNo,guardianNo,reason,leaveDate,leaveTime,returnDate,returnTime,travelMode;
    private boolean isChecked=false;
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
    RequestDetails()
    {
        // Default constructor required for calls to DataSnapshot.getValue(Firebase.class)

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setGuardianNo(String guardianNo) {
        this.guardianNo = guardianNo;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    RequestDetails(String name, String branch, String rollno, String roomno, String phoneNo, String guardianNo, String reason, String leaveDate, String leaveTime, String returnDate, String returnTime, String travelMode) {
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

  }
    public String getName() {
        return name;
    }

    public String getBranch() {
        return branch;
    }

    public String getRollno() {
        return rollno;
    }

    public String getRoomno() {
        return roomno;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getGuardianNo() {
        return guardianNo;
    }

    public String getReason() {
        return reason;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public String getTravelMode() {
        return travelMode;
    }
}
