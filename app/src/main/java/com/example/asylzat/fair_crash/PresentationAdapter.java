package com.example.asylzat.fair_crash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asylzat.fair_crash.Fragments.MyAgenda;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PresentationAdapter extends ArrayAdapter<Presentation>{
    private List<Presentation> list;
    private Context context;
    TextView  presentationTitle,
            presentationName,
            presentationInfo,
            presentationTime,
            addPresentation;
    MyAgenda myAgenda;

    public PresentationAdapter(Context context, MyAgenda myAgenda, List<Presentation> list) {
        super(context, 0, list);
        this.list = list;
        this.context = context;
        this.myAgenda = myAgenda;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.presentation_item,parent,false
            );
        }

        final Presentation presentation = getItem(position);

        presentationTitle = (TextView)listItemView.findViewById(R.id.presentation_title);
        presentationName = (TextView)listItemView.findViewById(R.id.presentation_name);
        presentationTime = (TextView)listItemView.findViewById(R.id.presentation_time);
        addPresentation = (TextView)listItemView.findViewById(R.id.add_item);

        presentationTitle.setText(presentation.getTitle());
        presentationName.setText(presentation.getName());
        presentationTime.setText(String.valueOf(presentation.getTime()));

        addPresentation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.presentationEvents.add(presentation);
                notifyDataSetChanged();
                myAgenda.calculateTotal();
                Toast toast = Toast.makeText(getContext(), "Added!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return listItemView;
    }

}
