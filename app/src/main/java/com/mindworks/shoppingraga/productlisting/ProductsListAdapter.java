package com.mindworks.shoppingraga.productlisting;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindworks.shoppingraga.datastore.beans.Product;

import java.util.List;

import com.mindworks.shoppingraga.R;
import com.mindworks.shoppingraga.productdetail.ProductClickListener;
import com.mindworks.shoppingraga.productdetail.ProductDetailActivity;
import com.mindworks.shoppingraga.utils.Constants;

/**
 * Created by taru on 4/30/2017.
 */

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ViewHolder> {

    private List<Product> products;
    private ProductClickListener clickListener;

    public ProductsListAdapter(List<Product> products, ProductClickListener clickListener) {
        this.products = products;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        return new ViewHolder(card, clickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product item = products.get(position);
        holder.tvName.setText(item.getName());
        holder.tvProductId.setText("" + item.getId());
        holder.tvSalePrice.setText("" + item.getSalePrice());
        holder.tvName.setTag(item);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvName;
        public TextView tvProductId;
        public TextView tvSalePrice;
        public ImageView ivThumbnail;
        public ProductClickListener clickListener;

        public ViewHolder(View v, ProductClickListener clickListener) {
            super(v);
            v.setOnClickListener(this);
            tvName = v.findViewById(R.id.tv_product_name);
            tvProductId = v.findViewById(R.id.tv_product_id);
            tvSalePrice = v.findViewById(R.id.tv_product_sale_price);
            ivThumbnail = v.findViewById(R.id.iv_thumbnail);
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View view) {
            Product product = (Product) view.findViewById(R.id.tv_product_name).getTag();
            clickListener.onProductClicked(product);
        }
    }
}