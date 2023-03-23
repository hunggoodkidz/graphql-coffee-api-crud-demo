import React from 'react';
import { useQuery } from '@apollo/client';
import gql from 'graphql-tag';

const ALL_COFFEE_QUERY = gql`
  query {
    findAll {
      id
      name
      price
      desc
    }
  }
`;

function App() {
  const { loading, error, data } = useQuery(ALL_COFFEE_QUERY);

  return (
    <div style={{margin: 'auto', width: '80%', fontFamily: 'Arial'}}>
      <h1>Nhóm Pixel - Demo</h1>
      <h1 style={{textAlign: 'center',color: 'brown'}}>Spring Menu Coffee</h1>
      {loading ? (
        <p>Loading...</p>
      ) : error ? (
        <p>Chưa lấy dc data ~ Can't Fetch</p>
      ) : (
        <ul style={{textAlign: 'center', listStyle: 'none', padding: 0}}>
          {data.findAll.map((cafe) => (
            <li key={cafe.id} style={{borderBottom: '1px solid #ccc', padding: '1rem 0'}}>
              <h2 style={{margin: 0}}>{cafe.name}</h2>
              <p style={{margin: '0.5rem 0'}}>Mô tả: {cafe.desc}</p>
              <p style={{margin: '0.5rem 0'}}>Giá: {cafe.price}</p>
            </li>
          ))}
        </ul>
      )}

    </div>
  );
}

export default App;