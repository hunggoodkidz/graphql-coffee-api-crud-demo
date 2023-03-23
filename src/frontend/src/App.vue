<script setup>
import { watchEffect } from 'vue'
import { useQuery, useResult } from '@vue/apollo-composable'
import gql from 'graphql-tag'

const ALL_BOOKS_QUERY = gql`
  query {
        findAll{
          id
          name
          size
        }
      }
`;

const { result, loading, error } = useQuery(ALL_BOOKS_QUERY)
const books = useResult(result, [], result.allBooks);

watchEffect(() => {
  console.log(books.value);
})
</script>

<template>
  <h1>Spring Coffee</h1>
  <p v-if="error">{{ error }}</p>
  <p v-if="loading">Loading...</p>
  <ul v-else>
    <li v-for="book in books" :key="book.id">{{ book.name }}</li>
  </ul>
</template>

<style>

</style>
