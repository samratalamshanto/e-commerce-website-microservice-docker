package com.samratalam.productservice.dto;

public record ProductRequest(String productType,
                             String name,
                             String description,
                             Double price) {
}


//record to a traditional class without setters
// The reduced need for synchronization due to immutability can also contribute to faster execution times in multithreaded environments.
//to define immutable data classes
//By reducing boilerplate code and enhancing readability
//Whether you’re defining temporary data structures within methods, working with streams,
// or replacing plain data types with more descriptive structures