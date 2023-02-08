package contracts.alto.test

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Test Transaction"
    request {
        url "/altopay/qr-payment/payment"
        method(HttpMethod.POST.toString())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body("""
            {
                "command" : "qr-payment-credit2",
                "data": {
                    "test": 1
                }
            }
        """)
        bodyMatchers {
            jsonPath('$.data.test',byRegex(nonBlank()))
        }
    }

    response {
        status OK()
        body("""
             {
                "command" : "qr-payment-credit1",
                "response_code" : "001",
                "response_text" : "Success",
                "data" : {
                  "customer_reference_number" : "9995400081650526636647DZ2MVJ46",
                  "forwarding_customer_reference_number" : "643128673371",
                  "invoice_no" : "11876687761187668776",
                  "currency_code" : "IDR",
                  "amount" : 10,
                  "fee" : 0
                }
              }
        """)
    }
}





