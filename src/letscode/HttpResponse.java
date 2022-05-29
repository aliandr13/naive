package letscode;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private final static String NEW_LINE = "\r\n";

    private final Map<String, String> headers = new HashMap<>();
    private String body = "";
    private int statusCode = 200;
    private String status = "Ok";

    public HttpResponse() {
        this.headers.put(HttpHeader.SERVER, "naive");
        this.headers.put(HttpHeader.CONNECTION, "Close");
    }

    public HttpResponse addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpResponse addHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public String message() {
        StringBuilder builder = new StringBuilder();

        builder.append("HTTP/1.1 ")
                .append(statusCode)
                .append(" ")
                .append(status)
                .append(NEW_LINE);

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(NEW_LINE);
        }

        return builder
                .append(NEW_LINE)
                .append(body)
                .toString();
    }

    public byte[] getBytes() {
        return message().getBytes();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public HttpResponse setBody(String body) {
        this.headers.put(HttpHeader.CONTENT_LENGTH, String.valueOf(body.length()));
        this.body = body;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public HttpResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return setStatus(statusCode);
    }

    public String getStatus() {
        return status;
    }

    private HttpResponse setStatus(int statusCode) {
        switch (statusCode) {
            // 1xx
            case 100:
                status = "Continue";
                break;
            case 101:
                status = "Switching Protocols";
                break;
            case 102:
                status = "Processing";
                break;
            case 103:
                status = "Early Hints";
                break;

            // 2xx
            case 200:
                status = "OK";
                break;
            case 201:
                status = "Created";
                break;
            case 202:
                status = "Accepted";
                break;
            case 203:
                status = "Non-Authoritative Information";
                break;
            case 204:
                status = "No Content";
                break;
            case 205:
                status = "Reset Content";
                break;
            case 206:
                status = "Partial Content";
                break;
            case 207:
                status = "Multi-Status";
                break;
            case 208:
                status = "Already Reported";
                break;
            case 226:
                status = "IM Used";
                break;

            // 3xx
            case 300:
                status = "Multiple Choices";
                break;
            case 301:
                status = "Moved Permanently";
                break;
            case 302:
                status = "Found";
                break;
            case 303:
                status = "See Other";
                break;
            case 304:
                status = "Not Modified";
                break;
            case 307:
                status = "Temporary Redirect";
                break;
            case 308:
                status = "Permanent Redirect";
                break;

            // 4xx
            case 400:
                status = "Bad Request";
                break;
            case 401:
                status = "Unauthorized";
                break;
            case 402:
                status = "Payment Required";
                break;
            case 403:
                status = "Forbidden";
                break;
            case 404:
                status = "Not Found";
                break;
            case 405:
                status = "Method Not Allowed";
                break;
            case 406:
                status = "Not Acceptable";
                break;
            case 407:
                status = "Proxy Authentication Required";
                break;
            case 408:
                status = "Request Timeout";
                break;
            case 409:
                status = "Conflict";
                break;
            case 410:
                status = "Gone";
                break;
            case 411:
                status = "Length Required";
                break;
            case 412:
                status = "Precondition Failed";
                break;
            case 413:
                status = "Payload Too Large";
                break;
            case 414:
                status = "URI Too Long";
                break;
            case 415:
                status = "Unsupported Media Type";
                break;
            case 416:
                status = "Range Not Satisfiable";
                break;
            case 417:
                status = "Expectation Failed";
                break;
            case 418:
                status = "I'm a teapot";
                break;
            case 421:
                status = "Misdirected Request";
                break;
            case 422:
                status = "Unprocessable Entity";
                break;
            case 423:
                status = "Locked";
                break;
            case 424:
                status = "Failed Dependency";
                break;
            case 425:
                status = "Too Early";
                break;
            case 426:
                status = "Upgrade Required";
                break;
            case 428:
                status = "Precondition Required";
                break;
            case 429:
                status = "Too Many Requests";
                break;
            case 431:
                status = "Request Header Fields Too Large";
                break;
            case 451:
                status = "Unavailable For Legal Reasons";
                break;

            // 5xx
            case 500:
                status = "Internal Server Error";
                break;
            case 501:
                status = "Not Implemented";
                break;
            case 502:
                status = "Bad Gateway";
                break;
            case 503:
                status = "Service Unavailable";
                break;
            case 504:
                status = "Gateway Timeout";
                break;
            case 505:
                status = "HTTP Version Not Supported";
                break;
            case 506:
                status = "Variant Also Negotiates";
                break;
            case 507:
                status = "Insufficient Storage";
                break;
            case 508:
                status = "Loop Detected";
                break;
            case 510:
                status = "Not Extended";
                break;
            case 511:
                status = "Network Authentication Required";
                break;
        }

        if(statusCode != 200 && status.equalsIgnoreCase("ok")) status = "Unknown status code";

        return this;
    }
}
