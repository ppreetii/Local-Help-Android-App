package vssut.mca.preeti.mini_project_part2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestHolder>{
    private Context context;
    ArrayList<RequestDetails> requests;
    ArrayList<String> keySelected;
    public RequestAdapter(Context context, ArrayList<RequestDetails> requests) {
        this.context = context;
        this.requests = requests;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.single_request_detail,parent,false);
        return new RequestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {
        RequestDetails requestDetails=requests.get(position);
        holder.setDetails(requestDetails);
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
    public ArrayList<RequestDetails> getAll() {
        return requests;
    }

    public ArrayList<RequestDetails> getSelected(ArrayList<String> key) {
        ArrayList<RequestDetails> selected = new ArrayList<>();
        keySelected=new ArrayList<>();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).isChecked()) {
                selected.add(requests.get(i));
                keySelected.add(key.get(i));
            }
        }
        return selected;
    }
    public ArrayList<String> getSelectedKeyValues()
    {
       return keySelected;
    }
}

