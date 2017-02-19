require 'test_helper'

class VolunsControllerTest < ActionDispatch::IntegrationTest
  test "should get index" do
    get voluns_index_url
    assert_response :success
  end

end
