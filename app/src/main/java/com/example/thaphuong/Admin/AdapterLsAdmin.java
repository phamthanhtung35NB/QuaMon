package com.example.thaphuong.Admin;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thaphuong.Class.LS;
import com.example.thaphuong.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterLsAdmin extends RecyclerView.Adapter<AdapterLsAdmin.MyViewHolder> {
    private List<LS> itemListsLS;
    private final Context context;

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    public AdapterLsAdmin(List<LS> messagesList, Context context) {
        this.itemListsLS = messagesList;
        this.context = context;
        System.out.println("MessagesAdapter.MessagesAdapter");
    }

    @NonNull
    @Override
    public AdapterLsAdmin.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ls_admin, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLsAdmin.MyViewHolder holder, int position) {
        System.out.println("MessagesAdapter.onBindViewHolder");
        System.out.println("1");
        LS item = itemListsLS.get(position);
        System.out.println("2");
        holder.textFrom.setText(item.getName());
        System.out.println("3");
        holder.textContent.setText(item.getContent());
        String currentDateTimeStrings = item.getKey();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

        try {
            Date date = sdf.parse(currentDateTimeStrings);

            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            String time = timeFormat.format(date);
            System.out.println(time);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = dateFormat.format(date);
            System.out.println(dateStr);

            holder.textTime.setText(time + " " + dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        (R.drawable.aklogo, "Trường Khác");
//        (R.drawable.uet, "Trường Đại học Công Nghệ, ĐHQGHN");
//        (R.drawable.ump, "Trường Đại học Y Dược, ĐHQGHN");
//        (R.drawable.ued, "Trường Đại học Giáo dục, ĐHQGHN");
//        (R.drawable.sis, "Trường Khoa học liên ngành và Nghệ thuật, ĐHQGHN");
//        (R.drawable.ussh, "Trường Đại học Khoa học Xã hội và Nhân văn, ĐHQGHN");
//        (R.drawable.ueb, "Trường Đại học Kinh tế, ĐHQGHN");
//        (R.drawable.hsb, "Trường Quản trị và Kinh doanh, ĐHQGHN");
//        (R.drawable.ul, "Trường Đại học Luật, ĐHQGHN");
//        (R.drawable.ulis, "Trường Đại học Ngoại ngữ, ĐHQGHN");
//        (R.drawable.is, "Trường Quốc tế, ĐHQGHN");
//        (R.drawable.ifi, "Khoa Quốc tế Pháp Ngữ, ĐHQGHN");
//        (R.drawable.hus, "Trường Đại học Khoa học Tự Nhiên, ĐHQGHN");
//        (R.drawable.vju, "Trường Đại học Việt - Nhật, ĐHQGHN");
        String university = item.getUniversity();
        System.out.println("4");
        if (university != null) {
            switch (university) {
                case "Trường Đại học Công Nghệ, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.uet);
                    break;
                case "Trường Đại học Y Dược, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ump);
                    break;
                case "Trường Đại học Giáo dục, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ued);
                    break;
                case "Trường Khoa học liên ngành và Nghệ thuật, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.sis);
                    break;
                case "Trường Đại học Khoa học Xã hội và Nhân văn, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ussh);
                    break;
                case "Trường Đại học Kinh tế, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ueb);
                    break;
                case "Trường Quản trị và Kinh doanh, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.hsb);
                    break;
                case "Trường Đại học Luật, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ul);
                    break;
                case "Trường Đại học Ngoại ngữ, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ulis);
                    break;
                case "Trường Quốc tế, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.is);
                    break;
                case "Khoa Quốc tế Pháp Ngữ, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.ifi);
                    break;
                case "Trường Đại học Khoa học Tự Nhiên, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.hus);
                    break;
                case "Trường Đại học Việt - Nhật, ĐHQGHN":
                    holder.imageLogo.setImageResource(R.drawable.vju);
                    break;
                default:
                    holder.imageLogo.setImageResource(R.drawable.aklogo);
                    break;
            }
        } else {
            Toast.makeText(context, "University is null", Toast.LENGTH_SHORT).show();
        }
        holder.buttonXoa.setOnClickListener(v -> {
            showDialog(item.getKey(), item.getName(), item.getContent());
        });
    }

    public void updateList(List<LS> list) {

        this.itemListsLS = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return itemListsLS.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layout;
        private TextView textFrom,textContent,textTime;
        private CircleImageView imageLogo;
        Button buttonXoa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            textFrom = itemView.findViewById(R.id.textFrom);
            textContent = itemView.findViewById(R.id.textContent);
            imageLogo = itemView.findViewById(R.id.imageLogo);
            textTime = itemView.findViewById(R.id.textTime);
            buttonXoa = itemView.findViewById(R.id.buttonXoa);
        }

    }
    private void showDialog(String key, String name, String content) {
        System.out.println("showDialog");
        final Dialog dialog = new Dialog(context);
        System.out.println("showDialog2");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        System.out.println("showDialog3");
        dialog.setContentView(R.layout.dialog_center_canh_bao);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        if (Gravity.BOTTOM == Gravity.BOTTOM) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        TextView tvContent = dialog.findViewById(R.id.tvContent);
        tvContent.setText("Bạn có chắc chắn muốn xóa thông báo của " + name+ "\n với nội dung "+ content+" không?");
        Button btnClose = dialog.findViewById(R.id.btnClose);
        Button btnXoa = dialog.findViewById(R.id.btnXoa);
        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnXoa.setOnClickListener(v -> {
            // Xóa dữ liệu
            dbRef.child(key).removeValue();
            dialog.dismiss();
        });

        dialog.show();
    }
}