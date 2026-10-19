package com.lsm.chnt;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class AutoCompletePalabrasAdapter extends ArrayAdapter {

    private  List<Palabras> palabrasListFull;


    @NonNull
    @Override
    public Filter getFilter() {
        return palabrasFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_list,parent,false);
        }
        TextView textViewName=convertView.findViewById(R.id.idDato);
        TextView videoTextView=convertView.findViewById(R.id.idVideo);
        Palabras  palabraItem= (Palabras) getItem(position);

        if(palabraItem!=null){
            textViewName.setText(palabraItem.getPalabra());
            videoTextView.setText(palabraItem.getVideo());
        }


        return convertView;
    }

    public AutoCompletePalabrasAdapter(@NonNull Context context, @NonNull List palabrasList) {
        super(context,0, palabrasList);
        palabrasListFull= new ArrayList<>(palabrasList);
    }

    private Filter palabrasFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results= new FilterResults();
            List<Palabras> suggestions = new ArrayList<>();

            if(constraint==null|| constraint.length()==0){
                suggestions.addAll(palabrasListFull);
            }else{
                String filterPattern= constraint.toString().toLowerCase().trim();

                for(Palabras item:palabrasListFull){
                    String normalicedString = item.getPalabra().toLowerCase();
                    normalicedString=   Normalizer.normalize(normalicedString, Normalizer.Form.NFD);
                    normalicedString = normalicedString.replaceAll("[^\\p{ASCII}]", "");
                    if(normalicedString.contains(filterPattern)){
                        suggestions.add(item);
                    }
                }
            }
            results.values=suggestions;
            results.count=suggestions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List)results.values);
            notifyDataSetChanged();

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Palabras)resultValue).getPalabra();
        }
    };

    public Filter getPalabrasFilter() {
        return palabrasFilter;
    }

    public void setPalabrasFilter(Filter palabrasFilter) {
        this.palabrasFilter = palabrasFilter;
    }
}
