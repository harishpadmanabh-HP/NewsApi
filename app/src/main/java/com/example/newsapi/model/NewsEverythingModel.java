package com.example.newsapi.model;

import java.util.List;

public class NewsEverythingModel {

    /**
     * status : ok
     * totalResults : 5597
     * articles : [{"source":{"id":null,"name":"Cointelegraph.com"},"author":"Cointelegraph By Justin O\u2019Connell","title":"How Crypto Gambling Is Regulated Around the World","description":"Countries around the world take varying approaches to the crypto gambling and betting, with Japan perhaps having the most defined stance","url":"https://cointelegraph.com/news/how-crypto-gambling-is-regulated-around-the-world","urlToImage":"https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9iYTBkYmQ2OWMyYzJjYWJmNDY1OTM5MGNiYTAxNGE5NC5qcGc=.jpg","publishedAt":"2019-09-29T08:22:00Z","content":"The Japanese House of Representatives recently passed new crypto asset regulation affecting exchanges and custodians \u2014 the Payment Services Act and the Financial Instruments and Exchange Act. However, the country\u2019s crypto gambling industry still endures stric\u2026 [+6272 chars]"}]
     */

    private String status;
    private int totalResults;
    private List<ArticlesBean> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * source : {"id":null,"name":"Cointelegraph.com"}
         * author : Cointelegraph By Justin O’Connell
         * title : How Crypto Gambling Is Regulated Around the World
         * description : Countries around the world take varying approaches to the crypto gambling and betting, with Japan perhaps having the most defined stance
         * url : https://cointelegraph.com/news/how-crypto-gambling-is-regulated-around-the-world
         * urlToImage : https://images.cointelegraph.com/images/740_aHR0cHM6Ly9zMy5jb2ludGVsZWdyYXBoLmNvbS9zdG9yYWdlL3VwbG9hZHMvdmlldy9iYTBkYmQ2OWMyYzJjYWJmNDY1OTM5MGNiYTAxNGE5NC5qcGc=.jpg
         * publishedAt : 2019-09-29T08:22:00Z
         * content : The Japanese House of Representatives recently passed new crypto asset regulation affecting exchanges and custodians — the Payment Services Act and the Financial Instruments and Exchange Act. However, the country’s crypto gambling industry still endures stric… [+6272 chars]
         */

        private SourceBean source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;

        public SourceBean getSource() {
            return source;
        }

        public void setSource(SourceBean source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class SourceBean {
            /**
             * id : null
             * name : Cointelegraph.com
             */

            private Object id;
            private String name;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
