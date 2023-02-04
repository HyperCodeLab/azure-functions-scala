package wiki.dataengineering

import com.microsoft.azure.functions.annotation.{AuthorizationLevel, FunctionName, HttpTrigger}
import com.microsoft.azure.functions._

import java.util.Optional


case class InputMessage (field1: String, field2: Int)
case class OutputMessage (field3: String, field4: Int)

class TestFunctionWithInputOutput {

  @FunctionName("TestWithInputOutput")
  def run(@HttpTrigger (name = "req", methods = Array(HttpMethod.POST, HttpMethod.GET), authLevel = AuthorizationLevel.ANONYMOUS) request: HttpRequestMessage[Optional[InputMessage]], context: ExecutionContext): HttpResponseMessage = {

    context.getLogger.info("Function executed")

    val input = request.getBody.orElse(null)
    context.getLogger.info(s"Field 1: ${input.field1}")
    context.getLogger.info(s"Field 2: ${input.field2}")

    request.createResponseBuilder(HttpStatus.OK).body(OutputMessage(input.field1, input.field2)).build()
  }

}
