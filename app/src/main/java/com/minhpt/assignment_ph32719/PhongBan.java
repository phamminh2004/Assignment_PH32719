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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhongBan extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<PhongBanModel> arrPB = new ArrayList<>();
    PhongBanAdapter pb_adapter;
    ListView lv_pb;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phong_ban);
        toolbar = findViewById(R.id.toolbar);
        lv_pb = findViewById(R.id.lv_pb);
        toolbar.setTitle("Phòng ban");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        arrPB.add(new PhongBanModel(R.drawable.home, "Nhân sự"));
        arrPB.add(new PhongBanModel(R.drawable.home, "Hành chính"));
        arrPB.add(new PhongBanModel(R.drawable.home, "Đào tạo"));
        pb_adapter = new PhongBanAdapter(PhongBan.this, arrPB);
        lv_pb.setAdapter(pb_adapter);
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
                pb_adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pb_adapter.getFilter().filter(newText);
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

    public class PhongBanAdapter extends BaseAdapter implements Filterable {
        private Activity activity;
        private ArrayList<PhongBanModel> list, listOld;

        public PhongBanAdapter(Activity activity, ArrayList<PhongBanModel> list) {
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
            convertView = inflater.inflate(R.layout.item_pb, parent, false);
            ImageView img_home = convertView.findViewById(R.id.img_home);
            TextView tv_pb = convertView.findViewById(R.id.tv_pb);
            PhongBanModel pb_model = list.get(position);
            img_home.setImageResource(pb_model.getImg());
            tv_pb.setText(pb_model.getName());
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
                        ArrayList<PhongBanModel> listS = new ArrayList<>();
                        for (PhongBanModel pb : listOld) {
                            if (pb.getName().toLowerCase().contains(s.toLowerCase())) {
                                listS.add(pb);
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
                    list = (ArrayList<PhongBanModel>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }

}