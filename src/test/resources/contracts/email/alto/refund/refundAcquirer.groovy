package contracts.alto.refund

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description"Refund Acquirer"
    request {
        url "/v2/qr-payment/refund"
        method(HttpMethod.POST.toString())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            header("X-Alto-Key": "dev_akey_U1AQQ7Z1PP")
            header("X-Alto-Timestamp": "2019-07-19T09:19:44.954Z")
            header("X-ALto-Signature": "c7e40e65f54a14b15dcc5ce7ad72fb13ead8701b6c37f42d0fdbb91466849267")
        }
        body("""
            {
                "command": "qr-refund",
                "data": {
                    "customer_reference_number": "0013284239ZFX21",
                    "date_time": "2019-02-19 04:24:27.000Z",
                    "invoice_no": "0123456789",
                    "currency_code": "IDR",
                    "amount_refund": 50000,
                    "reference_number": "412412334255"
                }
            }
        """)
    }
    response {
        status OK()
        body("""
            {
                "command": "qr-refund",
                "response_code": "001",
                "response_text": "Success",
                "data": {
                    "customer_reference_number": "0013284239ZFX21",
                    "reference_number": "412412334255",
                    "invoice_no_refund": "0333456789",
                    "authorization_id ": "B5A561"
                }
            }
        """)
    }
}

