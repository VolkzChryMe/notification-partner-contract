package contracts.alto

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Transaction Payment Credit PAYCRED ISS 01"
    request {
        url "/v2/qr-payment/payment"
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
                "command": "qr-payment-credit",
                "data": {
                    "date_time": "2022-04-28 09:56:05.386Z",
                    "customer_reference_number": "133211213165111456547927TGEW11",
                    "authorization_id": "ek8hMb",
                    "currency_code": "IDR",
                    "amount": 10001,
                    "fee": 0,
                    "issuer_nns": "93600822",
                    "national_mid": "133211213",
                    "additional_data": "0703C01",
                    "terminal_label": "C01",
                    "merchant": {
                        "pan": "9360110100000000012",
                        "id": "133211213",
                        "criteria": "UME",
                        "name": "Kopi el' Nino",
                        "city": "JAKARTA SELATAN",
                        "mcc": "5812",
                        "postal_code": "12120",
                        "country_code": "ID"
                    },
                    "customer": {
                        "pan": "9360082230001999476",
                        "name": "TEST APRILA",
                        "account_type": "E-WALLET"
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
                "command": "qr-payment-credit",
                "response_code": "001",
                "response_text": "Success",
                "data": {
                    "customer_reference_number": "133211213165111456547927TGEW11",
                    "forwarding_customer_reference_number": "002462708038",
                    "invoice_no": "11876688771187668877",
                    "currency_code": "IDR",
                    "amount": 10001,
                    "fee": 0
                }
            }
        """)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}