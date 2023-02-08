package contracts.alto.transaction

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Cek Status Payment Acquirer Not Found"
    request {
        url "/altopay/qr-payment/status"
        method(HttpMethod.POST.toString())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            header("X-Alto-Key": "dev_akey_U1AQQ7Z1PP")
            header("X-Alto-Timestamp": "2019-07-19T09:19:44.954Z")
            header("X-ALto-Signature": "c7e40e65f54a14b15dcc5ce7ad72fb13ead8701b6c37f42d0fdbb91466849267")
            header("Authorization": "Basic Y2xpZW50X3VzZXJfaWQ6Y2xpZW50X3Bhc3N3b3Jk")
        }
        body("""
            {
                "command": "qr-check-status",
                "data": {
                    "date_time": "2019-02-19 04:24:27.000Z",
                    "customer_reference_number": "0013284239ZFX27"
                }
            }
        """)
    }
    response {
        status OK()
        body("""
            {
                "command": "qr-check-status",
                "response_code": "096",
                "response_text": "Requested data not found",
                "data": {
                    "customer_reference_number": "0013284239ZFX27"
                }
            }
        """)
    }
}

