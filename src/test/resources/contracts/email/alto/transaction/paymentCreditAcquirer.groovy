package contracts.alto.transaction

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Payment Credit Acquirer"
    request {
        url "/altopay/qr-payment/payment"
        method(HttpMethod.POST.toString())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
            header("X-Alto-Key": "dev_akey_U1AQQ7Z1PP")
            header("X-Alto-Timestamp": "2019-07-19T09:19:44.954Z")
            header("X-ALto-Signature": "c7e40e65f54a14b15dcc5ce7ad72fb13ead8701b6c37f42d0fdbb91466849267")
        }
        body("""
            {
                "command": "qr-payment-credit",
                "data": {
                    "date_time": "2019-02-19 04:24:27.000Z",
                    "customer_reference_number": "0013284239ZFX21",
                    "authorization_id": "B5A561",
                    "currency_code": "IDR",
                    "amount": 100500,
                    "fee": 500,
                    "issuer_nns": "93600919",
                    "acquirer_nns": "93600009",
                    "national_mid": "IN1019000000024",
                    "additional_data": "ABCDEFGHIJ",
                    "terminal_label": "K19",
                    "forwarding_customer_reference_number": "000004093892",
                    "merchant": {
                        "pan": "9360053509876543211",
                        "id": "190202011521008",
                        "criteria": "UMI",
                        "name": "Somay Mangga Dua",
                        "city": "Jakarta",
                        "mcc": "6600",
                        "postal_code": "10110",
                        "country_code": "ID"
                    },
                    "customer": {
                        "pan": "9360002319993788396",
                        "name": "Sebastian",
                        "account_type": "E-WALLET"
                    }
                }
            }
        """)
    }
    response {
        status OK()
        body("""
            {
                "command": "qr-payment-credit",
                "response_code": "001",
                "response_text": "Success",
                "data": {
                    "customer_reference_number": "0013284239ZFX21",
                    "invoice_no": "0123456789",
                    "currency_code": "IDR",
                    "amount": 100500,
                    "fee": 500
                }
            }
        """)
    }
}

