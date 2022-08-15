package vssut.mca.preeti.mini_project_part2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class RequestHolder extends RecyclerView.ViewHolder
{
    private TextView tvNameRoomno,tvBranchRollNo,tvSelfGuardNo,tvLeaveDateTime,tvReturnDateTime,tvReason;
    private ImageView imgCheck;

    public RequestHolder(View itemView) {
        super(itemView);
        tvNameRoomno = itemView.findViewById(R.id.tvNameRoomNo);
        tvBranchRollNo = itemView.findViewById(R.id.tvBranchRollNo);
        tvSelfGuardNo = itemView.findViewById(R.id.tvSelfGuardNo);
        tvLeaveDateTime = itemView.findViewById(R.id.tvLeaveDateTime);
        tvReturnDateTime = itemView.findViewById(R.id.tvReturnDateTime);
        tvReason = itemView.findViewById(R.id.tvReason);
        imgCheck=itemView.findViewById(R.id.imageView);
       // setDetails();

    }
    public void setDetails(final RequestDetails requestDetails)
    {
        imgCheck.setVisibility(requestDetails.isChecked() ? View.VISIBLE : View.GONE);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestDetails.setChecked(!requestDetails.isChecked());
                imgCheck.setVisibility(requestDetails.isChecked() ? View.VISIBLE : View.GONE);
            }
        });
        String nameRoomNo=requestDetails.getName()+ ",RoomNo:" +requestDetails.getRoomno();
        String branchRoolNo=requestDetails.getBranch()+"-"+requestDetails.getRollno();
        String selfGuardNo="S.No:"+requestDetails.getPhoneNo()+",G.No:"+requestDetails.getGuardianNo();
        String leaveDateTime="LeaveDateTime:"+requestDetails.getLeaveDate().concat(requestDetails.getLeaveTime());
        String returnDAteTime="ReturnDateTime:"+requestDetails.getReturnDate().concat(requestDetails.getReturnTime());
        String reason="Reason:"+requestDetails.getReason();
        tvNameRoomno.setText(nameRoomNo);
        tvBranchRollNo.setText(branchRoolNo);
        tvSelfGuardNo.setText(selfGuardNo);
        tvLeaveDateTime.setText(leaveDateTime);
        tvReturnDateTime.setText(returnDAteTime);
        tvReason.setText(reason);
    }

}

