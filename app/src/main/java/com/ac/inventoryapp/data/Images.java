package com.ac.inventoryapp.data;


import com.ac.inventoryapp.R;

public enum Images {
    CLOUD("Cloud", R.drawable.cloud),
    FLOWER("Flower", R.drawable.flower),
    SMILE("Smile", R.drawable.smile);

    private String text;
    private int imageResource;

    Images(String text, int imageResource) {
        this.text = text;
        this.imageResource = imageResource;
    }

    public static Images valueOf(int imageResource) {
        if (imageResource == CLOUD.imageResource) {
            return CLOUD;
        } else if (imageResource == FLOWER.imageResource) {
            return FLOWER;
        } else {
            return SMILE;
        }
    }

    @Override
    public String toString() {
        return text;
    }

    public int getImageResource() {
        return imageResource;
    }
}
