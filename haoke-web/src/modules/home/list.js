import React from 'react';
import { withRouter } from 'react-router';
import { Icon,Item } from 'semantic-ui-react';
import config from '../../common.js';
import axios from 'axios';
import ApolloClient from "apollo-boost";
import gql from "graphql-tag";

const client = new ApolloClient({
  uri: "http://127.0.0.1:18080/graphql"
});

//定义查询
const QUERY_LIST = gql`
  query HouseResourcesList($pageSize: Int, $page: Int) {
    HouseResourcesList(pageSize: $pageSize, page: $page) {
      list {
        id
        pic
        title
        coveredArea
        orientation
        floor
        rent
      }
    }
  }
`;

class HouseList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      listData: [],
      typeName: '',
      type: null,
      loadFlag: false
    };
  }

  goBack = () => {
    console.log(this.props.history)
    this.props.history.goBack();
  }
  componentDidMount = () => {
    const {query} = this.props.location.state;
    this.setState({
      typeName:query.name,
      type: query.type
    })

    // 查询
    client.query({query: QUERY_LIST, variables: {"pageSize":3,"page":1}}).then(result => {
      this.setState({
        listData:result.data.HouseResourcesList.list,
        loadFlag: true
      });
    });

    // axios.post('/homes/list',{
    //   home_type: query.type
    // }).then(ret=>{
    //   this.setState({
    //     listData: ret.data,
    //     loadFlag: true
    //   })
    // })

  }
  render() {
    let list = null;
    if(this.state.loadFlag) {
      list = this.state.listData.map(item=>{
        return (
            <Item key={item.id}>
              <Item.Image src={item.pic.split(',')[0]}/>
              <Item.Content>
                <Item.Header>{item.title}</Item.Header>
                <Item.Meta>
                  <span className='cinema'>{item.coveredArea} 平方米/{item.orientation}/{item.floor}</span>
                </Item.Meta>
                <Item.Description>
                  上海
                </Item.Description>
                <Item.Description>{item.rent}</Item.Description>
              </Item.Content>
            </Item>
        )
      });
    }
    return (
        <div className = 'house-list' >
          <div className = "house-list-title">
            <Icon onClick={this.goBack} name = 'angle left' size = 'large'/>{this.state.typeName}
          </div>
          <div className = "house-list-content">
            <Item.Group divided unstackable>
              {list}
            </Item.Group>
          </div>
        </div>
    );
  }
}
export default withRouter(HouseList);
