package com.testehan.security;
/*
    Documentation about how this URL is constructed can be found on the following links :
        https://developers.google.com/chart/infographics/docs/qr_codes
        https://github.com/google/google-authenticator/wiki/Key-Uri-Format
 */
public class URLBuilder {

    private static final String baseURL = "https://chart.googleapis.com/chart?chs=";

    private final String base32Secret;
    private final String user;

    private final int qrSize;
    private final String issuer;
    private final int digits;
    private final int periodInSeconds;

    private URLBuilder(final Builder builder) {
        base32Secret = builder.base32Secret;
        user = builder.user;
        qrSize = builder.qrSize;
        issuer = builder.issuer;
        digits = builder.digits;
        periodInSeconds = builder.periodInSeconds;
    }

    public String getQrImageUrl(){
        StringBuilder sb = new StringBuilder(128);
        sb.append(baseURL + qrSize + "x" + qrSize + "&cht=qr&chl=").append("otpauth://totp/")
                .append(issuer).append(":").append(user)
                .append("?secret=").append(base32Secret)
                .append("&issuer=").append(issuer)
                .append("&digits=").append(digits)
                .append("&period=").append(periodInSeconds);
        return sb.toString();
    }

    public static class Builder {
        //required / mandatory
        private final String base32Secret;
        private final String user;

        //optional
        private int qrSize = 200;
        private String issuer = "testehanApp";
        private int digits = 6;
        private int periodInSeconds = 30;

        public Builder(String base32Secret, String user) {

            this.base32Secret = base32Secret;
            this.user = user;
        }

        public Builder withQrSize(int qrSize) {
            this.qrSize = qrSize;
            return this;
        }

        public Builder withIssuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public Builder withDigits(int digits) {
            this.digits = digits;
            return this;
        }

        public Builder withPeriodInSeconds(int periodInSeconds) {
            this.periodInSeconds = periodInSeconds;
            return this;
        }

        public URLBuilder build() {
            return new URLBuilder(this);
        }

    }
}
