package com.minhpt.assignment_ph32719;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NhanVien extends AppCompatActivity {
    ListView lv_nhanvien;
    SearchView searchView;
    Toolbar toolbar;
    ArrayList<NhanVienModel> arrNV = new ArrayList<>();
    NhanVienAdapter nhanVienAdapter;
    int index = -1;
    String FILE_NAME = "employee.txt";

    ActivityResultLauncher<Intent> updateData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 1) {
                        NhanVienModel nhanVienModel = (NhanVienModel) result.getData().getSerializableExtra("nhanvien");
                        arrNV.set(index, nhanVienModel);
                        nhanVienAdapter.notifyDataSetChanged();
                        luuDulieu();
                    }
                }
            });

    public void luuDulieu() {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrNV);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {

        }
    }

    public void docDulieu() {
        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            arrNV = (ArrayList<NhanVienModel>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhan_vien);
        lv_nhanvien = findViewById(R.id.lv_nhanvien);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Nhân viên");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        docDulieu();
        if (arrNV.size() == 0) {
            arrNV.add(new NhanVienModel("NV001", "Nguyễn Văn Linh", "Hành chính"));
            arrNV.add(new NhanVienModel("NV002", "Nguyễn Thị Mai", "Nhân sự"));
            arrNV.add(new NhanVienModel("NV003", "Phạm Nam", "Đào tạo"));
            arrNV.add(new NhanVienModel("NV004", "Quốc Anh", "Hành chính"));
        }
        luuDulieu();
        nhanVienAdapter = new NhanVienAdapter(NhanVien.this, arrNV);
        lv_nhanvien.setAdapter(nhanVienAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nhanVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                nhanVienAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public class NhanVienAdapter extends BaseAdapter implements Filterable {

        private Activity activity;
        private ArrayList<NhanVienModel> list, listOld;

        public NhanVienAdapter(Activity activity, ArrayList<NhanVienModel> list) {
            this.activity = activity;
            this.list = list;
            this.listOld = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_nv, parent, false);
            TextView txt_maNV = convertView.findViewById(R.id.txt_maNV);
            TextView txt_ten = convertView.findViewById(R.id.txt_ten);
            TextView txt_pb = convertView.findViewById(R.id.txt_pb);
            Button btn_update = convertView.findViewById(R.id.btn_update);
            Button btn_delete = convertView.findViewById(R.id.btn_delete);
            NhanVienModel nhanVienModel = list.get(position);
            txt_maNV.setText("Mã NV: " + nhanVienModel.getMaNV());
            txt_ten.setText("Họ tên: " + nhanVienModel.getHoten());
            txt_pb.setText("Phòng ban: " + nhanVienModel.getPb());
            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = position;
                    Intent intent = new Intent(getApplicationContext(), UpdateNhanVien.class);
                    intent.putExtra("nhanvien", arrNV.get(position));
                    updateData.launch(intent);
                    luuDulieu();
                }
            });
            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                    luuDulieu();
                }
            });
            return convertView;
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    String s = constraint.toString();
                    if (s.isEmpty()) {
                        list = listOld;
                    } else {
                        ArrayList<NhanVienModel> listS = new ArrayList<>();
                        for (NhanVienModel nv : listOld) {
                            if (nv.getHoten().toLowerCase().contains(s.toLowerCase())) {
                                listS.add(nv);
                            }
                        }
                        list = listS;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = list;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    list = (ArrayList<NhanVienModel>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }

}