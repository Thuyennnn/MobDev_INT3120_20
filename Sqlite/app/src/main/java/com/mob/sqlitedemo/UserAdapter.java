package com.mob.sqlitedemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> mListUSer;
    IClickItemUser iClickItemUser;

    public interface IClickItemUser{
        void UpdateUser(User user);
    }

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }

    public void setData(List<User>list) {
        this.mListUSer = list;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mListUSer.get(position);
        if(user == null) {
            return;
        }

        holder.tvUserName.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
        Log.d("sqlite debug", "adapter " + user.getAddress());
        holder.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemUser.UpdateUser(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListUSer != null) {
            return mListUSer.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserName;
        private TextView tvAddress;
        private Button btnUpdateUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            btnUpdateUser = itemView.findViewById(R.id.btnUpdateUser);
        }
    }
}
