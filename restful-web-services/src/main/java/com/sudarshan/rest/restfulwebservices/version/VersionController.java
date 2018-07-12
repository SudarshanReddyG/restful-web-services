package com.sudarshan.rest.restfulwebservices.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	//Different URI header versioning
	@GetMapping("/v1/fetchPerson")
	public PersonV1 fetchPersonV1() {
		return new PersonV1("Sudarshan Reddy");
	}

	@GetMapping("/v2/fetchPerson")
	public PersonV2 fetchPersonV2() {
		return new PersonV2(new Name("Sudarshan", "Reddy"));
	}
	//Using parameter versioning
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 fetchParamPersonV1() {
		return new PersonV1("Sudarshan Reddy");
	}

	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 fetchParamPersonV2() {
		return new PersonV2(new Name("Sudarshan", "Reddy"));
	}

	//Using header parameter
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 fetchHeaderPersonV1() {
		return new PersonV1("Sudarshan Reddy");
	}

	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 fetchHeaderPersonV2() {
		return new PersonV2(new Name("Sudarshan", "Reddy"));
	}

	//Using Accept header or MIME type versioning, uses header value as Accept -> application/com.sudarshan-v1+json
	@GetMapping(value="/person/produces", produces="application/com.sudarshan-v1+json")
	public PersonV1 fetchProducesPersonV1() {
		return new PersonV1("Sudarshan Reddy");
	}

	@GetMapping(value="/person/produces", produces="application/com.sudarshan-v2+json")
	public PersonV2 fetchProducesPersonV2() {
		return new PersonV2(new Name("Sudarshan", "Reddy"));
	}

}
