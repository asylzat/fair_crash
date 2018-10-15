package com.example.asylzat.fair_crash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        companyLink = (TextView)listItemView.findViewById(R.id.company_link);
        companyTime = (TextView)listItemView.findViewById(R.id.company_time);
        addCompany = (TextView)listItemView.findViewById(R.id.add_item);

        companyName.setText(company.getName());
        companyTime.setText(String.valueOf(company.getDuration()));

        companyLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = company.getLink();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });

        Picasso.with(context).load(company.getLogo())
                .resize(400, 400)
                .centerInside().into(companyLogo);


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
