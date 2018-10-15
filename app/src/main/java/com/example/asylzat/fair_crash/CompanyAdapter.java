package com.example.asylzat.fair_crash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyAdapter extends ArrayAdapter<Company> {
    private List<Company> list;
    private Context context;
    TextView companyName,
             companyInfo,
             companyLink,
             companyTime,
             addCompany;
     ImageView companyLogo;

    public CompanyAdapter(Context context, List<Company> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.company_item,parent,false
            );
        }

        final Company company = getItem(position);

        companyName = (TextView)listItemView.findViewById(R.id.company_name);
        companyLogo = (ImageView) listItemView.findViewById(R.id.company_logo);
        companyInfo = (TextView)listItemView.findViewById(R.id.company_info);
        companyLink = (TextView)listItemView.findViewById(R.id.company_link);
        companyTime = (TextView)listItemView.findViewById(R.id.company_time);
        addCompany = (TextView)listItemView.findViewById(R.id.add_item);

        companyName.setText(company.getName());
//        companyInfo.setText(company.getInfo());
        companyTime.setText(String.valueOf(company.getDuration()));
        companyLink.setText(company.getLink());
        Picasso.with(context).load(company.getLogo())
                .resize(200, 200)
                .centerCrop().into(companyLogo);


        //OnClick listeners for all the buttons on the ListView Item

        addCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.events.add(company);
                notifyDataSetChanged();
            }
        });

        return listItemView;
    }

}
