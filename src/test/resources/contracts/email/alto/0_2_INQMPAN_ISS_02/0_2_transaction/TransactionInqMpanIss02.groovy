package contracts.alto

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Transaction Payment Credit INQMPAN ISS 02"
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
                    "date_time": "2022-04-27 16:28:11.504Z",
                    "customer_reference_number": "9995400081651051692230DBC87GZH",
                    "authorization_id": "hd0Efo",
                    "currency_code": "IDR",
                    "amount": 12000,
                    "fee": 0,
                    "issuer_nns": "93600822",
                    "national_mid": "IN1019000000002",
                    "additional_data": "0703D01",
                    "terminal_label": "999540008",
                    "merchant": {
                        "pan": "9360110100998790988",
                        "id": "999540008",
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
                    "customer_reference_number": "9995400081651051692230DBC87GZH",
                    "forwarding_customer_reference_number": "422251415565",
                    "invoice_no": "11876688721187668872",
                    "currency_code": "IDR",
                    "amount": 12000,
                    "fee": 0
                }
            }
        """)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}

