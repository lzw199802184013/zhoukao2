package com.example.lzw.zhoukao2.mvp.model;

import java.util.List;

public class GridViewBean {
    private List<ListBean> data;

    public List<ListBean> getData() {
        return data;
    }

    public void setData(List<ListBean> data) {
        this.data = data;
    }
    public class  ListBean{
        private List<Data3Bean> list;

        public List<Data3Bean> getList() {
            return list;
        }

        public void setList(List<Data3Bean> list) {
            this.list = list;
        }
        public  class Data3Bean{
            private  String images;
            private String title;
            private  String price;

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
