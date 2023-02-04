package wiki.dataengineering

import com.microsoft.azure.functions.{ExecutionContext, HttpMethod, HttpRequestMessage, HttpResponseMessage, HttpStatus}
import com.microsoft.azure.functions.annotation.{AuthorizationLevel, FunctionName, HttpTrigger}

import java.util.Optional

class TestFunction {

  @FunctionName("Test")
  def run(@HttpTrigger (name = "req", methods = Array(HttpMethod.POST, HttpMethod.GET), authLevel = AuthorizationLevel.ANONYMOUS) request: HttpRequestMessage[Optional[String]], context: ExecutionContext): HttpResponseMessage = {

    context.getLogger.info("Function executed")

    context.getLogger.info(request.getHttpMethod.toString)
    context.getLogger.info(request.getUri.toString)
    context.getLogger.info(request.getBody.toString)

    request.createResponseBuilder(HttpStatus.OK).body("It Works!").build()
  }

}
