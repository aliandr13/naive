package letscode;

public class Main {
    public static void main(String[] args) {
        new Server((req, resp) -> {
            String url = req.getUrl();

            if(url.startsWith("/debug")) {
                url = url.replaceFirst("/debug\\?", "");

                StringBuilder params = new StringBuilder();
                params.append("Request params: ");

                String[] splitedUrl = url.split("&");
                for(int i = 0; i < splitedUrl.length; i++) {
                    String[] paramSplit = splitedUrl[i].split("=");
                    if(paramSplit.length == 1) continue;

                    if(paramSplit[0].equalsIgnoreCase("code")) resp.setStatusCode(Integer.parseInt(paramSplit[1]));

                    params.append(paramSplit[0]).append(" ").append(paramSplit[1]);
                    if((i+1) != splitedUrl.length) params.append(" | ");
                }

                return String.format(
                        "<html><body><h1>%s %s</h1><h1>%s</h1></body></html>",
                        resp.getStatus(),
                        resp.getStatusCode(),
                        params.toString().equals("Request params: ") ? "" : params.toString()
                );
            }

            return "<html><body><h1>Hello, naive</h1></body></html>";
        }).bootstrap();
    }
}
