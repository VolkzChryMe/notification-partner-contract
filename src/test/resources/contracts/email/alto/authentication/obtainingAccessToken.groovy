package contracts.alto.authentication

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType

Contract.make {
    description "Get an API Access Token"
    request {
        url "/api/auth/token"
        method HttpMethod.POST.toString()
        headers {
            accept("application/json")
            contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            header("Authorization" : $(c(regex(nonEmpty()))))
        }
        body("""
            {
                "grant_type": "client_credential"
            }
        """)
    }
    response {
        status OK()
        body("""
            {
                "access_token": "eyJhbGciOiJIUzI1NiJ9.-3hLQYQ168WGyHyRUJ_EUJvoON9rML-I4oUeMgGYNHc",
                "token_type": "Bearer",
                "expires_in": 7200
            }
        """)
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
    }
}

