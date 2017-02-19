require 'test_helper'

class OngsControllerTest < ActionDispatch::IntegrationTest
  test "should get index" do
    get ongs_index_url
    assert_response :success
  end

end
