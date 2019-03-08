package com.tech.futureteric.feedbacklibrary.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.irshulx.Editor;
import com.github.irshulx.models.EditorTextStyle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.tech.futureteric.feedbacklibrary.R;
import com.tech.futureteric.feedbacklibrary.constants.LibEnums;
import com.tech.futureteric.feedbacklibrary.model.Forum;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.tech.futureteric.feedbacklibrary.constants.LibConstants.BUNDLE_USER_UID;
import static com.tech.futureteric.feedbacklibrary.database.FireStoreUtils.addFeatureRequest;
import static com.tech.futureteric.feedbacklibrary.utils.ValidationUtils.isEditTextInputValid;


public class CreateFeatureRequestFragment extends Fragment {

    private static final String ARG_SECTION_NAME = "section_name";
    private View rootView;

    static CreateFeatureRequestFragment newInstance(String sectionName) {
        CreateFeatureRequestFragment fragment = new CreateFeatureRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NAME, sectionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_email_forum, container, false);
        setupView();
        return rootView;
    }

    private void setupView() {
        EventBus.getDefault().register(this);
        Editor description_editor = rootView.findViewById(R.id.editor_description);

        description_editor.setHeadingTypeface(getHeadingTypeface());
        description_editor.setContentTypeface(getContentface());
        description_editor.setDividerLayout(R.layout.tmpl_divider_layout);
        description_editor.setEditorImageLayout(R.layout.tmpl_image_view);
        description_editor.setListItemLayout(R.layout.tmpl_list_item);

        rootView.findViewById(R.id.action_h1).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.H1));

        rootView.findViewById(R.id.action_h2).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.H2));

        rootView.findViewById(R.id.action_h3).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.H3));

        rootView.findViewById(R.id.action_bold).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.BOLD));

        rootView.findViewById(R.id.action_Italic).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.ITALIC));

        rootView.findViewById(R.id.action_indent).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.INDENT));

        rootView.findViewById(R.id.action_outdent).setOnClickListener(v ->
                description_editor.updateTextStyle(EditorTextStyle.OUTDENT));

        rootView.findViewById(R.id.action_bulleted).setOnClickListener(v ->
                description_editor.insertList(false));

        rootView.findViewById(R.id.action_color).setOnClickListener(v ->
                description_editor.updateTextColor("#FF3333"));

        rootView.findViewById(R.id.action_unordered_numbered).setOnClickListener(v ->
                description_editor.insertList(true));

        rootView.findViewById(R.id.action_hr).setOnClickListener(v ->
                description_editor.insertDivider());

        rootView.findViewById(R.id.action_insert_image).setOnClickListener(v ->
                description_editor.openImagePicker());

        rootView.findViewById(R.id.action_insert_link).setOnClickListener(v ->
                description_editor.insertLink());

        rootView.findViewById(R.id.action_map).setOnClickListener(v ->
                description_editor.insertMap());

        rootView.findViewById(R.id.action_erase).setOnClickListener(v ->
                description_editor.clearAllContents());

        description_editor.render("<p>Hello man, whats up!</p>");
    }

    private Map<Integer, String> getContentface() {
        Map<Integer, String> typefaceMap = new HashMap<>();
        typefaceMap.put(Typeface.NORMAL, "fonts/Lato-Medium.ttf");
        typefaceMap.put(Typeface.BOLD, "fonts/Lato-Bold.ttf");
        typefaceMap.put(Typeface.ITALIC, "fonts/Lato-MediumItalic.ttf");
        typefaceMap.put(Typeface.BOLD_ITALIC, "fonts/Lato-BoldItalic.ttf");
        return typefaceMap;
    }

    private Map<Integer, String> getHeadingTypeface() {
        Map<Integer, String> typefaceMap = new HashMap<>();
        typefaceMap.put(Typeface.NORMAL, "fonts/GreycliffCF-Bold.ttf");
        typefaceMap.put(Typeface.BOLD, "fonts/GreycliffCF-Heavy.ttf");
        typefaceMap.put(Typeface.ITALIC, "fonts/GreycliffCF-Heavy.ttf");
        typefaceMap.put(Typeface.BOLD_ITALIC, "fonts/GreycliffCF-Bold.ttf");
        return typefaceMap;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirebaseFirestore db) {
        EventBus.getDefault().unregister(this);
        FloatingActionButton send_fab = rootView.findViewById(R.id.fab_sendForum);

        send_fab.setImageDrawable(new IconicsDrawable(getContext())
                .icon(GoogleMaterial.Icon.gmd_done)
                .color(Color.WHITE)
                .sizeDp(24));
        send_fab.setOnClickListener(v -> {
            EditText title = ((TextInputLayout) rootView.findViewById(R.id.textInputLayout_subject)).getEditText();
            Editor description_editor = rootView.findViewById(R.id.editor_description);

            // TODO refactor addFeatureRequest() if there is more than one use of this fragment
            assert title != null;
            if (isEditTextInputValid(title, LibEnums.TEXT))
                addFeatureRequest(db, new Forum(Objects.requireNonNull(
                        getArguments()).getString(BUNDLE_USER_UID), title.getText().toString()
                        , description_editor.getContentAsHTML()));
        });
    }

}