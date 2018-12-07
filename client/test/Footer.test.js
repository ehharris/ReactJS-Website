import './enzyme.config.js'
import React from 'react'
import { mount, shallow } from 'enzyme'
import Footer from '../src/components/Marginals/Footer.js'


const startProps = {
  'type': [undefined, "add-company-footer", "application-width", "row", "col-xs-12 col-sm-6 col-md-4", "company-sub-widget", "col-xs-12 col-sm-6 col-md-4", "company-sub-widget", "add-footer", "application-width", "footer-copyright wrapper", "copyright-text wrapper-left", undefined, "wrapper-right"],
};

/* Test example using a pre-defined function */
function testInput() {
  const foot = shallow((
    <Footer />
  ));

  let actual = [];
  foot.find('div').map((element) => actual.push(element.prop('className')));
  expect(actual).toEqual(startProps.type);
}

test('Check to see if table gets made correctly (Function)', testInput);