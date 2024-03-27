package net.somta.juggle.starter.impl;

import net.somta.core.helper.JsonSerializeHelper;
import net.somta.core.protocol.ResponseDataResult;
import net.somta.juggle.starter.model.FlowResultModel;
import net.somta.juggle.starter.model.FlowTriggerDataParam;
import net.somta.juggle.starter.properties.JuggleOpenProperties;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author husong
 */
public class JuggleTemplateImpl implements IJuggleTemplate{

    private static final String TRIGGER_FLOW_URL = "/v1/open/flow/trigger/%s/%s";
    private static final String GET_ASYNC_FLOW_RESULT_URL = "/v1/open/flow/getAsyncFlowResult/%s";

    private PoolingHttpClientConnectionManager connectionManager;
    private final JuggleOpenProperties juggleOpenProperties;
    public JuggleTemplateImpl(JuggleOpenProperties juggleOpenProperties) {
        this.juggleOpenProperties = juggleOpenProperties;
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(20);
        connectionManager.setDefaultMaxPerRoute(5);
    }

    @Override
    public ResponseDataResult<FlowResultModel> triggerFlow(String flowVersion, String flowKey, FlowTriggerDataParam triggerData) throws IOException {
        String url = juggleOpenProperties.getServerAddr() + String.format(TRIGGER_FLOW_URL, flowVersion, flowKey);
        HttpClient httpClient =getHttpClient();
        HttpUriRequestBase request = new HttpGet(url);
        fillCommonHttpHeader(request, juggleOpenProperties.getAccessToken());
        if (triggerData.getFlowData() != null) {
            buildRequestParams(request,url,triggerData.getFlowData());
        }

        final HttpClientResponseHandler<ResponseDataResult<FlowResultModel>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            ResponseDataResult<FlowResultModel> result = JsonSerializeHelper.deserialize(resultContent, ResponseDataResult.class, FlowResultModel.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return result;
        };

        ResponseDataResult<FlowResultModel> response = httpClient.execute(request,responseHandler);
        return response;
    }

    @Override
    public ResponseDataResult<Map<String, Object>> getAsyncFlowResult(String flowInstanceId) throws IOException {
        String url = juggleOpenProperties.getServerAddr() + String.format(GET_ASYNC_FLOW_RESULT_URL, flowInstanceId);
        HttpClient httpClient =getHttpClient();
        HttpUriRequestBase request = new HttpGet(url);
        fillCommonHttpHeader(request, juggleOpenProperties.getAccessToken());

        final HttpClientResponseHandler<ResponseDataResult<Map<String, Object>>> responseHandler = response -> {
            HttpEntity entity = response.getEntity();
            String resultContent = EntityUtils.toString(entity);
            ResponseDataResult<Map<String, Object>> result = JsonSerializeHelper.deserialize(resultContent, ResponseDataResult.class, Map.class);
            if(entity != null){
                EntityUtils.consume(entity);
            }
            return result;
        };
        ResponseDataResult<Map<String, Object>> response = httpClient.execute(request,responseHandler);
        return response;
    }

    /**
     * get http client
     * @return HttpClient
     */
    private HttpClient getHttpClient() {
        HttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
        return httpClient;
    }

    /**
     *
     * @param httpRequest http request
     * @param url http url
     * @param url http params
     */
    private void buildRequestParams(HttpUriRequestBase httpRequest, String url, Map<String,Object> params) {
        List<NameValuePair> list = new ArrayList<>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            list.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        try {
            URI uri = new URIBuilder(new URI(url))
                    .addParameters(list)
                    .build();
            httpRequest.setUri(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * fill common http header
     * @param httpRequest http request
     * @param accessToken request access token
     */
    private void fillCommonHttpHeader(HttpUriRequestBase httpRequest, String accessToken){
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.addHeader("Juggle-Token",accessToken);
    }
}
