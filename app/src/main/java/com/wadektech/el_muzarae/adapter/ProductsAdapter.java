package com.wadektech.el_muzarae.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.wadektech.el_muzarae.R;
import com.wadektech.el_muzarae.pojos.Products;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    List<Products> productsList ;
    Context context ;
    private OnItemClickListener onItemListener ;
    public static final String TAG = "Adapter Class";

    public ProductsAdapter(List<Products> productsList, Context context, OnItemClickListener onItemListener) {
        this.productsList = productsList;
        this.context = context;
        this.onItemListener = onItemListener ;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_content_details, parent, false);
        return new ProductsAdapter.ProductsViewHolder(view , onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        Products products = productsList.get(position) ;
        holder.productPrice.setText(String.valueOf(products.getProductPrice()));
        holder.productName.setText(products.getProductName());

        if (products.getProductImage().equals("")) {
            holder.productImage.setImageResource(R.drawable.farmers);
        } else {

            Picasso.with(context)
                    .load(products.getProductImage())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(holder.productImage, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Picasso.with (context)
                                    .load (products.getProductImage())
                                    //using a default image when images are not loaded
                                    .error(R.drawable.farmers)
                                    .into (holder.productImage);
                        }
                    });
        }
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage ;
        private TextView  productName , productPrice ;
        OnItemClickListener onItemClickListener ;
        public ProductsViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.tv_product_name);
            productPrice = itemView.findViewById(R.id.tv_product_price);
            this.onItemClickListener = onItemClickListener ;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClicked(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClicked(int position);
    }
}
