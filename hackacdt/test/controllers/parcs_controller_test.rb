require 'test_helper'

class ParcsControllerTest < ActionDispatch::IntegrationTest
  test "should get index" do
    get parcs_index_url
    assert_response :success
  end

end
