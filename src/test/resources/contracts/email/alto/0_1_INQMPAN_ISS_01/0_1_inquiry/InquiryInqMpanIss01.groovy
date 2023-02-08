package contracts.alto

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Inquiry INQMPAN ISS 01"
    request {
        url "/v2/qr-payment/inquiry"
        method(HttpMethod.POST.toString())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            header("X-Alto-Key": "akey_tUQVEzBszDcKIpM9P39g")
            header("X-Alto-Timestamp": $(c(regex(nonEmpty()))))
            header("X-ALto-Signature": $(c(regex(nonEmpty()))))
            header("Authorization": $(c(regex(nonEmpty()))))
        }
        body("""
            {
              "command" : "qr-inquiry-mpan",
              "data" : {
                "date_time" : "2022-04-26 11:14:02.710Z",
                "customer_reference_number" : "1xjtqU",
                "mode" : "MULTI",
                "authorization_id" : "wacCq9",
                "issuer_nns" : "93600822",
                "national_mid" : "IN1019000000001",
                "terminal_label" : "IN1019000000001",
                "merchant" : {
                  "name" : "Kopi el' Nino",
                  "city" : "JAKARTA SELATAN",
                  "mcc" : "5812",
                  "postal_code" : "12120",
                  "country_code" : "ID"
                }
              }
            }
        """)
        bodyMatchers {
            jsonPath('$.data.customer_reference_number',byRegex(nonBlank()))
            jsonPath('$.data.authorization_id',byRegex(nonBlank()))
            jsonPath('$.data.date_time',byRegex(nonBlank()))
        }
    }
    response {
        status OK()
        body("""
            {
              "command" : "qr-inquiry-mpan",
              "response_code" : "001",
              "response_text" : "Success",
              "data" : {
                "customer_reference_number" : "1xjtqU",
                "forwarding_customer_reference_number" : "351860239035",
                "merchant_data" : [ {
                  "merchant_id" : "999540008",
                  "merchant_pan" : "936011010099879098",
                  "merchant_criteria" : "UME"
                } ]
              }
            }
        """)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}


