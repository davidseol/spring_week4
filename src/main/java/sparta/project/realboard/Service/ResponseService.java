package sparta.project.realboard.Service;


import org.springframework.stereotype.Service;
import sparta.project.realboard.Response.CommonResponse;
import sparta.project.realboard.Response.ListResponse;
import sparta.project.realboard.Response.SingleResponse;

import java.util.List;

@Service
public class ResponseService {

    public<T> SingleResponse<T> getSingleResponse(T data){
        SingleResponse singleResponse = new SingleResponse();
        singleResponse.data = data;

        if(singleResponse.data == null){
            setFailureresponse(singleResponse);
        }else{
            setSuccessResponse(singleResponse);
        }

        return singleResponse;
    }

    public<T> ListResponse<T> getListResponse(List<T> dataList){
        ListResponse listResponse = new ListResponse();
        listResponse.data = dataList;

        if(listResponse.data == null){
            setFailureresponse(listResponse);
        }else{
            setSuccessResponse(listResponse);
        }

        return listResponse;
    }

    void setSuccessResponse(CommonResponse response){
//        response.code=0;
        response.success=true;
        response.error="null";
    }

    void setFailureresponse(CommonResponse response){
//        response.code=0;
        response.success=false;
        response.error="에러 발생";
    }

}
